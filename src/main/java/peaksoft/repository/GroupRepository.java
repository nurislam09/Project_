package peaksoft.repository;

import peaksoft.model.Group;

import java.io.IOException;
import java.util.List;

public interface GroupRepository {
    List<Group> getAllGroup();

    List<Group> getAllGroup(Long courseId);

    Group getGroupById(Long id);

    void saveGroup(Long courseId, Group group);

    void updateGroup(Long id, Group group);

    void deleteGroup(Long id);

    void assignGroup(Long courseId, Long id) throws IOException;
}