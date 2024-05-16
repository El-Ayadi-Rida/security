package com.server.security.Iservice;

import com.server.security.dto.LoginDto;
import com.server.security.dto.RegisterDto;

public interface AuthIService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
