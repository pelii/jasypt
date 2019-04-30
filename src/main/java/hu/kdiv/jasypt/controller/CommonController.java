package hu.kdiv.jasypt.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import hu.kdiv.jasypt.model.dto.TestDto;
import hu.kdiv.jasypt.service.TestService;

@Controller
public class CommonController {

	@Autowired
	private TestService testService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("test", new TestDto());

		return "index";
	}

	@PostMapping(value = "/changedata", params = "action=save")
	public String save(@ModelAttribute TestDto testDto, Model model) {
		if (!StringUtils.isEmpty(testDto.getText())) {
			testDto.setId(null);
			testService.saveTest(testDto);
			model.addAttribute("test", new TestDto());
		}
		return "index";
	}

	@PostMapping(value = "/changedata", params = "action=decrypt")
	public String decrypt(@ModelAttribute TestDto testDto, Model model) {
		if (testDto.getId() != null) {
			model.addAttribute("test", testService.readById(testDto.getId()));
		}
		return "index";
	}

	@PostMapping(value = "/changedata", params = "action=download")
	public ResponseEntity<Resource> download(@ModelAttribute TestDto testDto, Model model) {
		ResponseEntity<Resource> response = null;

		if (testDto.getId() != null) {
			testDto = testService.readById(testDto.getId());
			Resource resource = new ByteArrayResource(testDto.getData());

			response = ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))//
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "file.txt" + "\"")//
					.body(resource);

		} else {
			response = ResponseEntity.ok().build();
		}
		model.addAttribute("test", testService.readById(testDto.getId()));
		return response;
	}

	@PostMapping(value = "/uploadFile")
	public String submit(@RequestParam("file") MultipartFile file, Model model) throws IOException {
		testService.saveFile(file.getBytes());
		model.addAttribute("test", new TestDto());
		return "index";
	}

}
