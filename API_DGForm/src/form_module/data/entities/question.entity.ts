import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export default class Question {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  name: string;

  @Column({ name: 'number_question' })
  numberQuestion: number;

  @Column()
  description: string;

  @Column({ name: 'quiz_id' })
  quizId: number;

  @Column({ name: 'question_type_id' })
  questionTypeId: number;
}
