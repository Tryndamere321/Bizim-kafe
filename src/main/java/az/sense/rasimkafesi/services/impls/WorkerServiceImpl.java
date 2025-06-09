package az.sense.rasimkafesi.services.impls;

import az.sense.rasimkafesi.dtos.workerDtos.WorkerCreateDto;
import az.sense.rasimkafesi.dtos.workerDtos.WorkerDto;
import az.sense.rasimkafesi.dtos.workerDtos.WorkerUpdateDto;
import az.sense.rasimkafesi.models.Worker;
import az.sense.rasimkafesi.repositories.WorkerRepository;
import az.sense.rasimkafesi.services.WorkerService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final ModelMapper modelMapper;
    private final Cloudinary cloudinary;

    public WorkerServiceImpl(WorkerRepository workerRepository, ModelMapper modelMapper, Cloudinary cloudinary) {
        this.workerRepository = workerRepository;
        this.modelMapper = modelMapper;
        this.cloudinary = cloudinary;
    }

    @Override
    public List<WorkerDto> getAllWorkers() {
        List<Worker> workers = workerRepository.findAll();
        List<WorkerDto> workerDtos = workers.stream().map(x -> modelMapper.map(x, WorkerDto.class)).toList();
        return workerDtos;
    }

    @Override
    public WorkerDto getWorkerById(Long id) {
        Worker worker = workerRepository.findById(id).orElseThrow();
        return modelMapper.map(worker, WorkerDto.class);
    }

    @Override
    public boolean createWorker(WorkerCreateDto workerCreateDto, MultipartFile image) {
        try {
            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            String photoUrl = (String) uploadResult.get("url");

            Worker worker = new Worker();
            worker.setPhotoUrl(photoUrl);
            worker.setName(workerCreateDto.getName());
            worker.setSurname(workerCreateDto.getSurname());
            worker.setPosition(workerCreateDto.getPosition());
            worker.setInformation(workerCreateDto.getInformation());
            workerRepository.save(worker);
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean updateWorker(WorkerUpdateDto workerUpdateDto, Long id, MultipartFile image) {
        try {
            Worker findWorker = workerRepository.findById(id).orElseThrow();
            if (image.isEmpty()) {
                findWorker.setPhotoUrl(workerUpdateDto.getPhotoUrl());
            } else {
                Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
                String photoUrl = (String) uploadResult.get("url");
                findWorker.setPhotoUrl(photoUrl);
            }
            findWorker.setName(workerUpdateDto.getName());
            findWorker.setSurname(workerUpdateDto.getSurname());
            findWorker.setInformation(workerUpdateDto.getInformation());
            findWorker.setPosition(workerUpdateDto.getPosition());
            workerRepository.save(findWorker);
            return true;

        } catch (IOException e) {
            return false;
        }
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
