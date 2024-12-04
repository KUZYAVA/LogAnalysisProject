package ru.Kuzevanov_Alexander.NauJava.domain.services.group;

import ru.Kuzevanov_Alexander.NauJava.data.models.Group;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.ExternalApiException;
import ru.Kuzevanov_Alexander.NauJava.domain.exceptions.GroupNotFoundException;

import java.util.List;

/**
 * Defines the service interface for managing groups.  This interface provides methods for
 * retrieving, updating, and refreshing group information, potentially interacting with external
 * APIs or data sources.
 */
public interface GroupService {

    /**
     * Loads the group data from an external source.
     *
     * @throws ExternalApiException If an error occurs during communication with the external API.
     */
    void load() throws ExternalApiException;

    /**
     * Retrieves a list of all group IDs.
     *
     * @return A list of Integer representing all group IDs.
     */
    List<Integer> findAllIds();

    /**
     * Retrieves a group by its title.
     *
     * @param title The title of the group to retrieve.
     * @return The Group object if found.
     * @throws GroupNotFoundException If a group with the given title is not found.
     */
    Group findByTitle(String title) throws GroupNotFoundException;

    /**
     * Changes the visibility status of a group.  The exact behavior of changing visibility
     * (e.g., public/private) is implementation-specific.
     *
     * @param title The title of the group whose visibility should be changed.
     * @throws GroupNotFoundException If a group with the given title is not found.
     */
    void changeVisibility(String title) throws GroupNotFoundException;
}
