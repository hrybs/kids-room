package ua.softserveinc.tc.dto;

import ua.softserveinc.tc.entity.Child;

import java.util.Date;

/**
 * Created by edward on 5/17/16.
 */
public class ChildDto {

    private Long id;
    private String firstName;
    private String lastName;
    private UserDto parentId;
    private Date dateOfBirth;
    private long age;
    private String comment;

    public ChildDto(Child child) {
        this.id = child.getId();
        this.firstName = child.getFirstName();
        this.lastName = child.getLastName();
        this.parentId = new UserDto(child.getParentId());
        this.dateOfBirth = child.getDateOfBirth();
        this.age = child.getAge();
        this.comment = child.getComment();
    }

}
