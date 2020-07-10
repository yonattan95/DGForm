import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { User } from './data/entities/user.entity';

@Injectable()
export default class UserService {
  constructor(
    @InjectRepository(User) private userRepository: Repository<User>,
  ) {}
  async newUser(user: User): Promise<boolean> {
    await this.userRepository.save(user);
    return true;
  }
  getUserById(id: number): Promise<User> {
    return this.userRepository.findOne({ id: id });
  }
  
}
