import { Column, Entity, PrimaryColumn } from 'typeorm';

@Entity()
export default class QuestionType {
  @PrimaryColumn()
  id: number;

  @Column()
  name: string;

  @Column()
  description: string;
}
