import {
  HttpStatus,
  Injectable,
  UnauthorizedException,
} from '@nestjs/common';
import { PassportStrategy } from '@nestjs/passport';
import { Strategy } from 'passport-local';
import ErrorResponseException from 'src/common/exceptions/error_response.exception';
import AuthService from './auth.service';

@Injectable()
export default class UserLocalStrategy extends PassportStrategy(
  Strategy,
  'local2',
) {
  constructor(private authService: AuthService) {
    super();
  }

  async validate(username: string, password: string): Promise<any> {
    const user = await this.authService.validateUser(
      username,
      password,
    );
    if (!user)
      throw new ErrorResponseException({
        errorMessage: 'usuario o contraseña invalida',
      });
    return user;
  }
}
