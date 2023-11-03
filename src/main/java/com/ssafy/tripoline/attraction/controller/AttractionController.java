package com.ssafy.tripoline.attraction.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.tripoline.attraction.model.dto.Attraction;
import com.ssafy.tripoline.attraction.model.dto.Gugun;
import com.ssafy.tripoline.attraction.model.dto.Sido;
import com.ssafy.tripoline.attraction.model.service.AttractionService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/attraction")
public class AttractionController {
	private AttractionService attractionService;

	public AttractionController(AttractionService attractionService) {
		this.attractionService = attractionService;
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Sido> sidos = attractionService.searchSido();
		log.debug("sidos =================> {}", sidos);
		model.addAttribute("sidos", sidos);
		return "attraction/attraction";
	}
	
	@ResponseBody
	@GetMapping("/findGugun")
	public List<Gugun> findGugun(int sidoCode) {
		List<Gugun> guguns = attractionService.searchGugun(sidoCode);
		log.debug("guguns =============> {}", guguns);
		return guguns;
	}
	
	@ResponseBody
	@GetMapping("/search")
	public List<Attraction> search(int sidoCode, int gugunCode, int contentTypeId) {
		List<Attraction> attractions = attractionService.searchByLocation(sidoCode, gugunCode, contentTypeId);
		log.debug("attractions ===============> {}", attractions);
		return attractions;
	}
}
