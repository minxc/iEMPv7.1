package org.flowable.ui.idm.rest.api;

import org.flowable.idm.api.Token;
import org.flowable.ui.common.service.exception.NotFoundException;
import org.flowable.ui.idm.model.TokenRepresentation;
import org.flowable.ui.idm.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiTokensResource {

    @Autowired
    protected TokenService tokenService;

    @RequestMapping(value = "/idm/tokens/{tokenId}", method = RequestMethod.GET, produces = { "application/json" })
    public TokenRepresentation getToken(@PathVariable String tokenId) {
        Token token = tokenService.findTokenById(tokenId);
        if (token == null) {
            throw new NotFoundException();
        } else {
            return new TokenRepresentation(token);
        }
    }

}
