import { Controller, Post, Request, UseGuards } from '@nestjs/common';
import { AuthGuard } from '@nestjs/passport';
import {
  ResponseAPI,
  SuccessResponse,
} from 'src/common/dto/response.dto';
import AuthService from './auth.service';
import JwtAuthGuard from './guards/jwt_auth.guard';
import LocalAuthGuard from './guards/local_auth.guard';

@Controller('auth')
export default class AuthController {
  constructor(private authService: AuthService) {}

  @UseGuards(LocalAuthGuard)
  @Post('login_user')
  async loginByUser(@Request() req): Promise<any> {
    return req.user;
  }

  @UseGuards(LocalAuthGuard)
  @Post('login_interviewer')
  async loginByInterviewer(
    @Request() req,
  ): Promise<ResponseAPI<any>> {
    const { user } = req;
    const token = await this.authService.login(user);
    return new SuccessResponse({
      interviewerId: user.id,
      username: user.username,
      accessToken: token,
    });
  }
}
