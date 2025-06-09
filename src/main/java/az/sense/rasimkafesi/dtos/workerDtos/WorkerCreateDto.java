package az.sense.rasimkafesi.dtos.workerDtos;

import lombok.Data;

@Data
public class WorkerCreateDto {
    private String name;
    private String surname;
    private String position;
    private String information;
    private String photoUrl;

}
