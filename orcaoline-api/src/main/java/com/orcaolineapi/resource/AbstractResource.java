package com.orcaolineapi.resource;

import org.springframework.web.bind.annotation.RestController;

import com.orcaolineapi.modelo.AbstractModel;

@RestController
public abstract class AbstractResource<T extends AbstractModel> {

}
