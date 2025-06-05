package az.sense.rasimkafesi.dtos.workerDtos;

import lombok.Data;

@Data
public class WorkerDto {
    private Long id;
    private String name;
    private String surname;
    private String position;
    private String information;
}
