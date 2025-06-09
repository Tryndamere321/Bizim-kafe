package az.sense.rasimkafesi.dtos.workerDtos;

import lombok.Data;

@Data
public class WorkerUpdateDto {
    private Long id;
    private String name;
    private String surname;
    private String position;
    private String information;
    private String photoUrl;

}
