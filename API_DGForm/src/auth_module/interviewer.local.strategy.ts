import { Injectable, UnauthorizedException } from '@nestjs/common';
import { PassportStrategy } from '@nestjs/passport';
import { Strategy } from 'passport-local';
import AuthService from './auth.service';

@Injectable()
export default class InterviewerLocalStrategy extends PassportStrategy(
  Strategy,
) {
  constructor(private authService: AuthService) {
    super();
  }

  async validate(username: string, password: string): Promise<any> {
    const interviewer = await this.authService.validateInterviewer(
      username,
      password,
    );
    if (!interviewer) throw new UnauthorizedException();
    return interviewer;
  }
}
