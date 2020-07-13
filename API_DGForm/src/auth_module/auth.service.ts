import { Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import InterviewerService from 'src/interviewer_module/interviewer.service';
import UserService from 'src/user_module/user.service';

@Injectable()
export default class AuthService {
  constructor(
    private userService: UserService,
    private interviewerService: InterviewerService,
    private jwtService: JwtService,
  ) {}

  async validateUser(username: string, password: string) {
    const user = await this.userService.getUserByUsername(username);
    if (user && user.password === password) {
      const { password, ...result } = user;
      return result;
    }
    return null;
  }
  async validateInterviewer(username: string, pass: string) {
    const user = await this.interviewerService.getinterviewerByUsername(
      username,
    );
    if (user && user.password === pass) {
      const { password, ...result } = user;
      return result;
    }
    return null;
  }

  async login(user) {
    const payload = { username: user.username, sub: user.userId };
    return {
      access_token: this.jwtService.sign(payload),
    };
  }
}
