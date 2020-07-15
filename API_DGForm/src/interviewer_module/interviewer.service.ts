import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import ErrorResponseException from 'src/common/exceptions/error_response.exception';
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
    const res = await this.interviewerRepository.save(interviewer);
    console.log(res);
    return res ? true : false;
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
    const res = await this.interviewerRepository.update(id, {
      state,
    });
    return res && res.affected > 0 ? true : false;
  }

  async updateInterviewerData(
    id: number,
    data: UpdateInterviewerI,
  ): Promise<boolean> {
    const res = await this.interviewerRepository.update({ id }, data);
    return res && res.affected > 0 ? true : false;
  }
}
