package com.hyunhii.dinnerForU.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.hyunhii.dinnerForU.controller.admin.form.FoodForm;
import com.hyunhii.dinnerForU.dto.FoodDto;
import com.hyunhii.dinnerForU.dto.FoodSearchCondition;
import com.hyunhii.dinnerForU.entity.Food;
import com.hyunhii.dinnerForU.entity.FoodType;
import com.hyunhii.dinnerForU.repositroy.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodService {

    private final FoodRepository foodRepository;

    private static final String BUCKET_NAME = System.getenv("BUCKET_NAME");
    private final Storage storage;

    @Transactional
    public void createFood(Food food) {
        foodRepository.save(food);
    }

    public List<FoodForm> getList() {
        List<Food> foods = foodRepository.findAll();

        List<FoodForm> foodForm
                = foods.stream().map(FoodForm::new).collect(Collectors.toList());

        return foodForm;
    }

    public Page<FoodDto> getListWithPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<FoodDto> result = foodRepository.search(pageRequest);

        return result;
    }

    public Page<FoodDto> getFoodList(FoodType type, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        FoodSearchCondition condition = new FoodSearchCondition();
        if (type != null) {
            condition.setFoodType(type);
        }
        Page<FoodDto> result = foodRepository.searchFood(condition, pageRequest);

        return result;
    }


    @Transactional
    public void updateFood(Long id, FoodDto foodForm) {
        Food food = foodRepository.findById(id).orElseThrow();

        food.changeFood(foodForm.getFoodName(), foodForm.getFoodType(), foodForm.getUseYN());
    }

    public FoodDto findDto(Long foodId){
        Food findFood = foodRepository.findById(foodId).orElseThrow();

        return new FoodDto(findFood.getId(), findFood.getFoodName(), findFood.getType(), findFood.getUseYN(), findFood.getFoodName());
    }

    public Food findOne(Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow();
        return food;
    }

    @Transactional
    public void deleteFood(Long foodId) {
        Food findFood = foodRepository.findById(foodId).orElseThrow();
        foodRepository.delete(findFood);
    }

    @Transactional
    public String uploadImg(MultipartFile file) throws Exception {
        String sourceFileName = file.getOriginalFilename();
//        String sourceFileExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();

        String src = sourceFileName;

        File destFile = new File(src);
//        File destFile = new File(sourceFileExtension);

        file.transferTo(destFile);

        return sourceFileName;
    }

    @Transactional
    public String uploadToGCP(MultipartFile file) throws Exception {

        String uuid = UUID.randomUUID().toString();
        String ext = file.getContentType();
//        String bucketName = "food-image-bucket-1";

        storage.create(BlobInfo.newBuilder(BUCKET_NAME, uuid)
                        .setContentType(ext)
                        .build(),
                file.getInputStream()
        );

        return uuid;

    }

}
