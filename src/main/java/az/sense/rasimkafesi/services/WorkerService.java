package az.sense.rasimkafesi.services;

import az.sense.rasimkafesi.dtos.workerDtos.WorkerCreateDto;
import az.sense.rasimkafesi.dtos.workerDtos.WorkerDto;
import az.sense.rasimkafesi.dtos.workerDtos.WorkerUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WorkerService {
    List<WorkerDto> getAllWorkers();
    WorkerDto getWorkerById(Long id);
    boolean createWorker(WorkerCreateDto workerCreateDto, MultipartFile image);
    boolean updateWorker(WorkerUpdateDto workerUpdateDto,Long id,MultipartFile image);
    boolean deleteWorker(Long id);
    WorkerUpdateDto findWorkerById(Long id);


}
