import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AuthModule } from 'src/auth_interviewer_module/auth.module';
import { Interviewer } from './data/entities/interviewer.entity';
import InterviewerController from './interviewer.controller';
import InterviewerService from './interviewer.service';

@Module({
  controllers: [InterviewerController],
  providers: [InterviewerService],
  imports: [TypeOrmModule.forFeature([Interviewer])],
  exports: [InterviewerService],
})
export class InterviewerModule {}
