package com.example.picket.service;

import com.example.picket.dto.InfoAddRequest;
import com.example.picket.dto.InfoResponse;
import com.example.picket.dto.PerformanceForm;
import com.example.picket.entity.Performance;
import com.example.picket.entity.PerformanceInfo;
import com.example.picket.repository.PerformanceInfoRepository;
import com.example.picket.repository.PerformanceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class  PerformanceService {
    private final PerformanceRepository performanceRepository;
    private final PerformanceInfoRepository performanceInfoRepository;

    @Value("${file.upload.dir}")
    private String uploadDir;

    //자세한 공연 정보 조회
    public PerformanceForm findInfo(String title){
        Performance performance = performanceRepository.findById(title).orElse(null);
        return performance != null ? performance.toForm() : null;
    }

    public List<InfoResponse> findInfoImg(String title, String sort){
        List<InfoResponse> performanceDetails = new ArrayList<>();


        for(PerformanceInfo performanceInfo : performanceInfoRepository.findAllByTitle(title)){
            switch (sort) {
                case "notice" -> {
                    if (performanceInfo.toInfoResponse().getSort().equals("notice")) {
                        performanceDetails.add(performanceInfo.toInfoResponse());
                    }
                }
                case "casting" -> {
                    if (performanceInfo.toInfoResponse().getSort().equals("casting")) {
                        performanceDetails.add(performanceInfo.toInfoResponse());
                    }
                }
                case "discount" -> {
                    if (performanceInfo.toInfoResponse().getSort().equals("discount")) {
                        performanceDetails.add(performanceInfo.toInfoResponse());
                    }
                }
                default -> {
                    if (performanceInfo.toInfoResponse().getSort().equals("schedule")) {
                        performanceDetails.add(performanceInfo.toInfoResponse());
                    }
                }
            }
        }
        performanceDetails.sort(Comparator.comparing(InfoResponse::getDetailNum));
        return performanceDetails;
    }

    //model에 정보 저장
    public void toModel(PerformanceForm performanceForm, Model model){
        if(performanceForm != null){
        model.addAttribute("title", performanceForm.getTitle());
        model.addAttribute("place", performanceForm.getPlace());
        model.addAttribute("dates", performanceForm.getDates());
        model.addAttribute("category", performanceForm.getCategory());
        model.addAttribute("price", performanceForm.getPrice());
        }
    }

    //공연 카테고리별 리스트 조회
    public List<PerformanceForm> findList(String category){
        return performanceRepository.findByCategory(category).stream().map(Performance::toForm).collect(Collectors.toList());
    }

    //공연 카테고리별 Carousel URL 무작위 선별
    public List<PerformanceForm> RandCarousel(List<PerformanceForm> performanceFormList){
        List<PerformanceForm> filteredList = performanceFormList.stream()
                .filter(item -> item.getCarouselImg() != null)
                .collect(Collectors.toCollection(ArrayList::new));

        Collections.shuffle(filteredList);
        List<PerformanceForm> randCarouselList = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            if(i < 5 && i < filteredList.size()) {
                randCarouselList.add(filteredList.get(i));
            }else if(i < 5 && i > filteredList.size()){
                randCarouselList.add(new PerformanceForm());
            }else{
                if(filteredList.size() > 1){
                    randCarouselList.add(filteredList.get(0));
                    randCarouselList.add(filteredList.get(1));
                }else if(filteredList.size() == 1){
                    randCarouselList.add(filteredList.get(0));
                    randCarouselList.add(new PerformanceForm());
                }else{
                    randCarouselList.add(new PerformanceForm());
                    randCarouselList.add(new PerformanceForm());
                }
            }
            if(randCarouselList.size() > 6) break;
        }
        return randCarouselList;
    };

    //공연 총 리스트 조회
    public List<PerformanceForm> findListAll(){
        return performanceRepository.findAll().stream().map(Performance::toForm).collect(Collectors.toList());
    }

    //공연 정보 삭제
    @Transactional
    public void deletePerformInfo(String title){
        Performance performance = performanceRepository.findById(title).orElse(null);
        List<PerformanceInfo> performanceInfos = performanceInfoRepository.findAllByTitle(title);

        if(performance != null){
            for(PerformanceInfo performanceInfo : performanceInfos){
                File file = new File(uploadDir + performanceInfo.getPerformInfoUrl());
                if (file.exists()) {
                    if (file.delete()) {
                        System.out.println("파일이 삭제되었습니다: " + file.getPath());
                    } else {
                        System.out.println("파일 삭제에 실패했습니다: " + file.getPath());
                    }
                }
            }

            File fileMain = new File(uploadDir + performance.getImgUrl());

            if (fileMain.exists()) {
                if (fileMain.delete()) {
                    System.out.println("파일이 삭제되었습니다: " + fileMain.getPath());
                } else {
                    System.out.println("파일 삭제에 실패했습니다: " + fileMain.getPath());
                }
            }

            File fileCarousel = new File(uploadDir + performance.getCarouselUrl());

            if (fileCarousel.exists()) {
                if (fileCarousel.delete()) {
                    System.out.println("파일이 삭제되었습니다: " + fileCarousel.getPath());
                } else {
                    System.out.println("파일 삭제에 실패했습니다: " + fileCarousel.getPath());
                }
            }
            performanceInfoRepository.deleteAllByTitle(title);
            performanceRepository.deleteById(title);
        }
    }

    //공연 정보 추가
    public void addPerformInfo(InfoAddRequest infoAddRequest) throws IOException {
        Long price = Long.parseLong(infoAddRequest.getPrice());
        Long runTime = Long.parseLong(infoAddRequest.getRunTime());
        String detailCategory = null;

        if(infoAddRequest.getCategory().equals("act")){
            detailCategory = "연극 > " + infoAddRequest.getDetailCategory();
        } else if(infoAddRequest.getCategory().equals("classic")) {
            detailCategory = "클래식 > " + infoAddRequest.getDetailCategory();
        } else if(infoAddRequest.getCategory().equals("concert")){
            detailCategory = "콘서트 > " + infoAddRequest.getDetailCategory();
        } else if(infoAddRequest.getCategory().equals("exhibit")){
            detailCategory = "전시회 > " + infoAddRequest.getDetailCategory();
        } else if(infoAddRequest.getCategory().equals("musical")){
            detailCategory = "뮤지컬 > " + infoAddRequest.getDetailCategory();
        }

        //mainImg 저장
        File directoryMain = new File(uploadDir + File.separator + "image" + File.separator + "category"
                + File.separator + infoAddRequest.getCategory() + File.separator + "main");
        if(!directoryMain.exists()){directoryMain.mkdirs();}
        String mainImgSaveUrl = "/image/category/" + infoAddRequest.getCategory() + "/main/" + infoAddRequest.getInfoMainImg().getOriginalFilename();
        String mainImgUrl = directoryMain.getPath() + File.separator + infoAddRequest.getInfoMainImg().getOriginalFilename();
        File saveMainFile = new File(mainImgUrl);
        infoAddRequest.getInfoMainImg().transferTo(saveMainFile);

        //carouselImg 저장
        File directoryCarousel = new File(uploadDir + File.separator + "image" + File.separator + "category"
                + File.separator + infoAddRequest.getCategory() + File.separator + "carousel");
        if(!directoryCarousel.exists()){directoryCarousel.mkdirs();}
        String carouselImgSaveUrl = "/image/category/" + infoAddRequest.getCategory() + "/carousel/" + infoAddRequest.getCarousel().getOriginalFilename();
        String catouselImgUrl = directoryCarousel.getPath() + File.separator + infoAddRequest.getCarousel().getOriginalFilename();
        File saveCarouselFile = new File(catouselImgUrl);
        infoAddRequest.getCarousel().transferTo(saveCarouselFile);

        Performance performance = new Performance(infoAddRequest.getTitle(), infoAddRequest.getPlace(), infoAddRequest.getDate(),
                detailCategory,  price, mainImgSaveUrl, carouselImgSaveUrl, runTime, infoAddRequest.getAgeGrade(),
                infoAddRequest.getCategory(), null);

        //공연 테이블 insert
        performanceRepository.save(performance);

        //infoImg 저장
        File directoryInfo = new File(uploadDir + File.separator + "image" + File.separator + "category"
                + File.separator + infoAddRequest.getCategory() + File.separator + "info");
        if(!directoryInfo.exists()){directoryInfo.mkdirs();}
        String infoImgSaveUrl = "/image/category/" + infoAddRequest.getCategory() + "/info/";

        String[] sortArr = new String[infoAddRequest.getInfoExplainSortArr().length];

        int index = 0;
        for(String sort : infoAddRequest.getInfoExplainSortArr()){
            sortArr[index++] = sort;
        }

        index = 0;
        int detailNum = 1;
        for(MultipartFile infoImg : infoAddRequest.getInfoExplainImgArr()){
            performanceInfoRepository.save(new PerformanceInfo(null, performance,
                    infoImgSaveUrl + infoImg.getOriginalFilename(), sortArr[index++], detailNum++));
            String infoImgUrl = directoryInfo.getPath() + File.separator + infoImg.getOriginalFilename();

            File saveInfoFile = new File(infoImgUrl);
            infoImg.transferTo(saveInfoFile);

            if(index >= infoAddRequest.getInfoExplainImgArr().length){
                break;
            }

            if(detailNum > 1 && !sortArr[index].equals(sortArr[index - 1])){
                detailNum = 1;
            }
        }
    }

}
