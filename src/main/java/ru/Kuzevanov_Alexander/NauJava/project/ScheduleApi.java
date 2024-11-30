package ru.Kuzevanov_Alexander.NauJava.project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import ru.Kuzevanov_Alexander.NauJava.project.exceptions.NetworkException;
import ru.Kuzevanov_Alexander.NauJava.project.model.GroupRemote;
import ru.Kuzevanov_Alexander.NauJava.project.model.ScheduleRemote;
import ru.Kuzevanov_Alexander.NauJava.project.model.ScheduleEventRemote;

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
public class ScheduleApi {

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new GsonBuilder().create();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("2024-11-26"); // todo return yyyy-MM-dd

    public List<ScheduleEventRemote> getScheduleEvents() throws NetworkException {
        List<Integer> groupIds = getGroupIds();
        List<ScheduleEventRemote> scheduleEvents = new ArrayList<>();
        String currentDate = LocalDateTime.now().format(formatter);
        for (int i = 0; i < 1; i++) { // todo не забыть добавить size
            int id = groupIds.get(i);
            try {
                String getScheduleEventsUrl = String.format("https://urfu.ru/api/v2/schedule/groups/%s/schedule?date_gte=%s&date_lte=%s", id, currentDate, currentDate);
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(getScheduleEventsUrl)).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                ScheduleRemote schedule = gson.fromJson(response.body(), ScheduleRemote.class);
                ScheduleEventRemote[] events = schedule.getEvents();
                scheduleEvents.addAll(Arrays.asList(events));
            } catch (IOException | InterruptedException exception) {
                throw new NetworkException("Не удалось корректно считать страницу!", exception);
            }
        }
        return scheduleEvents;
    }

    public Integer getGroupId(String groupTitle) throws NetworkException {
        List<Integer> groupIds = new ArrayList<>();
        try {
            String searchGroupByTitle = String.format("https://urfu.ru/api/v2/schedule/groups?search=%s", groupTitle);
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(searchGroupByTitle)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            GroupRemote[] groups = gson.fromJson(response.body(), GroupRemote[].class);
            for (GroupRemote group : groups) {
                groupIds.add(group.getId());
            }
        } catch (IOException | InterruptedException exception) {
            throw new NetworkException("Не удалось корректно считать страницу!", exception);
        }
        return groupIds.getFirst();
    }

    private List<Integer> getGroupIds() throws NetworkException {
        List<Integer> groupIds = new ArrayList<>();
        for (int course = 1; course <= 6; course++) {
            try {
                String getGroupsUrl = "https://urfu.ru/api/v2/schedule/divisions/62404/groups?course=%s".formatted(course); // todo 62404 и exception-ы
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(getGroupsUrl)).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                GroupRemote[] groups = gson.fromJson(response.body(), GroupRemote[].class);
                for (GroupRemote group : groups) {
                    groupIds.add(group.getId());
                }
            } catch (IOException | InterruptedException exception) {
                throw new NetworkException("Не удалось корректно считать страницу!", exception);
            }
        }
        return groupIds;
    }
}

