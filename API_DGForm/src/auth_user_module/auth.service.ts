import { Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import UserService from 'src/user_module/user.service';

@Injectable()
export default class AuthService {
  constructor(
    private userService: UserService,
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

  async login(user) {
    const payload = { username: user.username, sub: user.userId };
    const token = this.jwtService.sign(payload);
    //devuelve solo el token
    return token;
  }
}
