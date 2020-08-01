import { Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import UserService from 'src/user_module/user.service';
import { hashString } from 'src/common/helpers/security.helper';

@Injectable()
export default class AuthService {
  constructor(
    private userService: UserService,
    private jwtService: JwtService,
  ) {}

  async validateUser(username: string, password: string) {
    const user = await this.userService.getUserByUsername(username);
    const passHash = hashString(password);
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
