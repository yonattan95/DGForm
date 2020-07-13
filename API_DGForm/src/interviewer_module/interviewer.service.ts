import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Interviewer } from './data/entities/interviewer.entity';
import {
  InterviewerI,
  UpdateInterviewerI,
} from './data/interfaces/interviewer.interface';

@Injectable()
export default class InterviewerService {
  constructor(
    @InjectRepository(Interviewer)
    private interviewerRepository: Repository<Interviewer>,
  ) {}
  async newInterviewer(interviewer: Interviewer): Promise<boolean> {
    await this.interviewerRepository.save(interviewer);
    return true;
  }
  async getinterviewerById(id: number): Promise<Interviewer> {
    return this.interviewerRepository.findOne({ id: id });
  }
  async getinterviewerByUsername(
    username: string,
  ): Promise<Interviewer> {
    return this.interviewerRepository.findOne({ username });
  }

  async changeState({ id, state }): Promise<boolean> {
    await this.interviewerRepository.update(id, { state });
    return true;
  }

  async updateInterviewerData(
    id: number,
    data: UpdateInterviewerI,
  ): Promise<boolean> {
    await this.interviewerRepository.update({ id }, data);
    return true;
  }
}
