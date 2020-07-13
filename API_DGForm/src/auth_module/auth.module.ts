import { Module } from '@nestjs/common';
import { JwtModule } from '@nestjs/jwt';
import { PassportModule } from '@nestjs/passport';
import { InterviewerModule } from 'src/interviewer_module/interviewer.module';
import { UserModule } from 'src/user_module/user.module';
import AuthController from './auth.controller';
import AuthService from './auth.service';
import { jwtConstants } from './constants';
import JwtAuthGuard from './guards/jwt_auth.guard';
import InterviewerLocalStrategy from './interviewer.local.strategy';
import JwtStrategy from './jwt.strategy';
import UserLocalStrategy from './user.local.strategy';

@Module({
  controllers: [AuthController],
  providers: [
    AuthService,
    UserLocalStrategy,
    InterviewerLocalStrategy,
    JwtStrategy,
  ],
  imports: [
    PassportModule,
    UserModule,
    InterviewerModule,
    JwtModule.register({
      secret: jwtConstants.secret,
      signOptions: { expiresIn: '60s' },
    }),
  ],
  // exports: [AuthService],
})
export class AuthModule {}
