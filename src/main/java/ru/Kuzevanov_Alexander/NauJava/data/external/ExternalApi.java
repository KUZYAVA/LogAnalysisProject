package ru.Kuzevanov_Alexander.NauJava.data.external;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.data.external.models.GroupResponse;
import ru.Kuzevanov_Alexander.NauJava.data.external.models.GroupScheduleResponse;
import ru.Kuzevanov_Alexander.NauJava.data.external.models.ScheduleEventResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ExternalApi {

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new GsonBuilder().create();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("2024-11-26");// todo return yyyy-MM-dd

    private static final Integer DIVISION_ID = 62404; // УрФУ, ИЕНиМ, Школа бакалавриата (включает 5 департаментов)

    public List<ScheduleEventResponse> getScheduleEvents(List<Integer> groupIds) throws ExternalApiException {
        List<ScheduleEventResponse> scheduleEvents = new ArrayList<>();
        String currentDate = LocalDateTime.now().format(formatter);
        for (int i = 0; i < 1; i++) { // todo не забыть добавить size
            int id = groupIds.get(i);
            try {
                String getScheduleEventsUrl = String.format("https://urfu.ru/api/v2/schedule/groups/%s/schedule?date_gte=%s&date_lte=%s", id, currentDate, currentDate);
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(getScheduleEventsUrl)).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                GroupScheduleResponse groupSchedule = gson.fromJson(response.body(), GroupScheduleResponse.class);
                ScheduleEventResponse[] events = groupSchedule.events();
                scheduleEvents.addAll(Arrays.asList(events));
            } catch (IOException | InterruptedException exception) {
                throw new ExternalApiException();
            }
        }
        return scheduleEvents;
    }

    public List<GroupResponse> getGroups() throws ExternalApiException {
        List<GroupResponse> groups = new ArrayList<>();
        for (int course = 1; course <= 6; course++) {
            try {
                String getGroupsUrl = "https://urfu.ru/api/v2/schedule/divisions/%s/groups?course=%s".formatted(DIVISION_ID, course);
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(getGroupsUrl)).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                GroupResponse[] groupArray = gson.fromJson(response.body(), GroupResponse[].class);
                groups.addAll(Arrays.asList(groupArray));
            } catch (IOException | InterruptedException exception) {
                throw new ExternalApiException();
            }
        }
        return groups;
    }
}

