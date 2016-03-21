package com.bath.controller;

import com.bath.dto.MarkDto;
import com.bath.entity.Mark;
import com.bath.repository.BathRepository;
import com.bath.repository.MarkRepository;
import com.bath.repository.ServiceRepository;
import com.bath.service.BathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/")
public class MarkController
{
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private BathRepository bathRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private BathService bathService;


    @RequestMapping(value = "bathes/{bathId}/marks", method = RequestMethod.GET)
    public @ResponseBody Iterable<Mark> findByBath(
            @PathVariable Long bathId)
    {
        return markRepository.findByBathId(bathId);
    }

    @RequestMapping(value = "bathes/{bathId}/marks/average", method = RequestMethod.GET)
    public @ResponseBody Double findAverageByBath(
            @PathVariable Long bathId)
    {
        return markRepository.findAvgByBathId(bathId);
    }

    @RequestMapping(value = "bathes/{bathId}/users/{userId}/marks", method = RequestMethod.GET)
    public @ResponseBody Iterable<Mark> findByBathAndUser(
            @PathVariable Long bathId,
            @PathVariable Long userId)
    {
        return markRepository.findByBathIdAndUserId(bathId, userId);
    }

    @RequestMapping(value = "bathes/{bathId}/users/{userId}/marks/average", method = RequestMethod.GET)
    public @ResponseBody Double findAvgByBathAndUser(
            @PathVariable Long bathId,
            @PathVariable Long userId)
    {
        return markRepository.findAvgByBathIdAndUserId(bathId, userId);
    }

    @RequestMapping(value = "bathes/{bathId}/marks", method = RequestMethod.PUT)
    public @ResponseBody Mark setMark(
            @PathVariable Long bathId,
            @RequestBody MarkDto markDto)
    {
        Mark mark = new Mark();
        mark.setBath(bathRepository.findOne(bathId));
        mark.setService(serviceRepository.findOne(markDto.getServiceId()));
        mark.setValue(markDto.getValue());
        return bathService.addMark(mark);
    }

    @RequestMapping(value = "marks/{markId}", method = RequestMethod.DELETE)
    public void setMark(
            @PathVariable Long markId)
    {
        markRepository.delete(markId);
    }

}
