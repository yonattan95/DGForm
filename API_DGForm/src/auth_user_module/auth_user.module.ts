import { Module } from '@nestjs/common';
import { JwtModule } from '@nestjs/jwt';
import { PassportModule } from '@nestjs/passport';
import { jwtConstants } from 'src/common/auth/constants';
import { UserModule } from 'src/user_module/user.module';
import AuthController from './auth.controller';
import AuthService from './auth.service';
import JwtStrategy from './jwt.strategy';
import UserLocalStrategy from './user.local.strategy';

@Module({
  providers: [AuthService, UserLocalStrategy, JwtStrategy],
  controllers: [AuthController],
  imports: [
    UserModule,
    PassportModule,
    JwtModule.register({
      secret: jwtConstants.secret,
      signOptions: { expiresIn: '1h' },
    }),
  ],
})
export class AuthUserModule {}
