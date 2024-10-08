package com.healthmed.application.adapters.controllers;

import com.healthmed.application.adapters.controllers.exception.NotFoundException;
import com.healthmed.domain.dtos.doctor.DoctorDTO;
import com.healthmed.domain.ports.interfaces.DoctorServicePort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorController {
    private final DoctorServicePort servicePort;

    public DoctorController(DoctorServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @PostMapping
    void registerDoctor(@RequestBody @Valid DoctorDTO doctorDTO) {
        servicePort.registerDoctor(doctorDTO);
    }

    @GetMapping(value = "/{cpf}")
    @PreAuthorize("hasAuthority('ROLE_MEDICO')")
    DoctorDTO findDoctorByCpf(@PathVariable String cpf) {
        return servicePort.findDoctorByCpf(cpf);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_MEDICO')")
    List<DoctorDTO> findDoctors() {
        return servicePort.findAll();
    }
}
