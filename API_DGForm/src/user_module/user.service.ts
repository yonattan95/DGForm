import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { User } from './data/entities/user.entity';
import {
  NewUserI,
  UpdateUserI,
} from './data/interfaces/user.interface';

@Injectable()
export default class UserService {
  constructor(
    @InjectRepository(User) private userRepository: Repository<User>,
  ) {}
  async newUser(user: NewUserI): Promise<boolean> {
    await this.userRepository.save(user);
    return true;
  }
  getUserById(id: number): Promise<User> {
    return this.userRepository.findOne({ id: id });
  }
  getUserByUsername(username: string): Promise<User> {
    return this.userRepository.findOne({ username });
  }

  async changeState(id: number, state: number): Promise<boolean> {
    await this.userRepository.update({ id }, { state });
    return true;
  }

  async updateUserData(
    id: number,
    data: UpdateUserI,
  ): Promise<boolean> {
    await this.userRepository.update({ id }, data);
    return true;
  }
}
