package az.sense.rasimkafesi.services.impls;

import az.sense.rasimkafesi.dtos.workerDtos.WorkerCreateDto;
import az.sense.rasimkafesi.dtos.workerDtos.WorkerDto;
import az.sense.rasimkafesi.dtos.workerDtos.WorkerUpdateDto;
import az.sense.rasimkafesi.models.Worker;
import az.sense.rasimkafesi.repositories.WorkerRepository;
import az.sense.rasimkafesi.services.WorkerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final ModelMapper modelMapper;

    public WorkerServiceImpl(WorkerRepository workerRepository, ModelMapper modelMapper) {
        this.workerRepository = workerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<WorkerDto> getAllWorkers() {
       List<Worker> workers = workerRepository.findAll();
       List<WorkerDto> workerDtos = workers.stream().map(x->modelMapper.map(x,WorkerDto.class)).toList();
       return workerDtos;
    }

    @Override
    public WorkerDto getWorkerById(Long id) {
        Worker worker = workerRepository.findById(id).orElseThrow();
        return modelMapper.map(worker, WorkerDto.class);
    }

    @Override
    public boolean createWorker(WorkerCreateDto workerCreateDto) {
        try {
            Worker worker = new Worker();
            worker.setName(workerCreateDto.getName());
            worker.setSurname(workerCreateDto.getSurname());
            worker.setPosition(workerCreateDto.getPosition());
            worker.setInformation(workerCreateDto.getInformation());
            workerRepository.save(worker);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateWorker(WorkerUpdateDto workerUpdateDto, Long id) {
       Worker worker=workerRepository.findById(id).orElseThrow();
       worker.setName(workerUpdateDto.getName());
       worker.setSurname(workerUpdateDto.getSurname());
       worker.setPosition(workerUpdateDto.getPosition());
       worker.setInformation(workerUpdateDto.getInformation());
       workerRepository.save(worker);
       return true;
    }

    @Override
    public boolean deleteWorker(Long id) {
       workerRepository.deleteById(id);
       return true;
    }

    @Override
    public WorkerUpdateDto findWorkerById(Long id) {
        Worker worker = workerRepository.findById(id).orElseThrow();
        return modelMapper.map(worker, WorkerUpdateDto.class);
    }
}
