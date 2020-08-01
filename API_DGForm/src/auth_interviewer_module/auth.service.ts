import { Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import InterviewerService from 'src/interviewer_module/interviewer.service';
import UserService from 'src/user_module/user.service';
import { hashString } from 'src/common/helpers/security.helper';

@Injectable()
export default class AuthService {
  constructor(
    private interviewerService: InterviewerService,
    private jwtService: JwtService,
  ) {}

  async validateInterviewer(username: string, pass: string) {
    const user = await this.interviewerService.getinterviewerByUsername(
      username,
    );
    const passHash = hashString(pass);

    if (user && user.password === passHash) {
      const { password, ...result } = user;
      return result;
    }
    return null;
  }

  async login(user) {
    const payload = { username: user.username, sub: user.userId };
    const token = this.jwtService.sign(payload);
    //devuelve solo el token
    return token;
  }
}
