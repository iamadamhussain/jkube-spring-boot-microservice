package com.fss;

import static io.cloudevents.core.CloudEventUtils.mapData;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.data.PojoCloudEventData;
import io.cloudevents.jackson.PojoCloudEventDataMapper;


@Path("/demob")
public class DemoBController {

	public static final String FSS_EVENT_TYPE = "fss.myapplication";

	@Autowired
	ObjectMapper objectMapper;

// just to test application below method
	@GET
	@Path("greetmessage")
	public String greetMessage() {
		System.out.println("-------------greetMessage---------------");

		return " Hi -Welcome";
	}

	@POST
	@Path("send_user")
	public Response sendUserEnvent(@RequestBody CloudEvent inputEvent) {
		System.out.println("----------Data-----------------");

		// check if getType not equal fss.myapplication,then throw error
		if (!inputEvent.getType().equals(FSS_EVENT_TYPE)) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN)
					.entity("Event type should be \"" + FSS_EVENT_TYPE + "\" but is \"" + inputEvent.getType() + "\"")
					.build();
		}
		PojoCloudEventData<User> cloudEventData = mapData(inputEvent,
				PojoCloudEventDataMapper.from(objectMapper, User.class));

		if (cloudEventData == null) {
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN)
					.entity("Event should contain the user").build();
		}

		User user = cloudEventData.getValue();
		user.setAge(user.getAge() + 1);
		System.out.println("---------Printing -User Data-----------------" + user.toString());

		CloudEvent outputEvent = CloudEventBuilder.from(inputEvent)
				.withData(PojoCloudEventData.wrap(user, objectMapper::writeValueAsBytes)).build();

		return Response.ok(outputEvent).build();
	}

}
