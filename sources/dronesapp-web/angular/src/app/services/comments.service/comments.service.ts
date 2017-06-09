import { Injectable } from '@angular/core';
import {RequestService} from '../request.service/request.service';
import {CommentModel} from './comment.interface';

@Injectable()
export class CommentsService {
  comments = [
    {
      author: 'Gillespie Joseph',
      text: 'Veniam irure velit enim mollit aliqua cupidatat. Ut aute esse aute veniam aliqua aliqua est. Cupidatat irure in laboris ea commodo cillum sunt nisi ut quis nisi. Eiusmod minim adipisicing quis quis mollit do. Deserunt incididunt laborum proident et cupidatat veniam sunt exercitation eiusmod non ullamco mollit officia. Mollit quis occaecat incididunt non consequat.',
      date: '12/16/16'
    },
    {
      author: 'Rosanne Duffy',
      text: 'Occaecat irure elit labore elit nisi minim non dolore eiusmod irure. Anim ex labore non et irure culpa nostrud aliqua aute. Quis et adipisicing est occaecat commodo sint. Anim aliquip qui deserunt exercitation minim non. Nostrud eiusmod nisi ad ea quis deserunt incididunt anim dolor.',
      date: '12/25/16'
    },
    {
      author: 'Holmes Hunt',
      text: 'Commodo dolor incididunt laboris enim nisi in duis aliqua. Minim ullamco nulla enim nostrud. Commodo tempor deserunt ullamco ut elit sunt enim.',
      date: '1/01/17'
    },
    {
      author: 'Jessie Blake',
      text: 'Elit anim eiusmod pariatur aliquip dolor. Voluptate culpa nostrud amet excepteur ad pariatur eiusmod adipisicing. Ad sit dolor quis adipisicing exercitation dolore aute veniam laboris non laborum aute. Mollit magna exercitation anim dolore cillum cupidatat laborum.',
      date: '2/16/17'
    },
    {
      author: 'Barber Clayton',
      text: 'Commodo magna pariatur qui dolore dolore ad exercitation. Do dolor adipisicing aliquip occaecat culpa voluptate in elit aute amet officia esse non ad. Fugiat proident elit nulla aute. Qui duis irure anim commodo id cillum minim minim commodo proident. Qui elit velit anim eu ex sunt sit minim in eiusmod consequat sit adipisicing est. Aliqua proident cillum excepteur incididunt duis nostrud et officia aliqua id duis. Excepteur non anim culpa duis.',
      date: '3/22/17'
    },
    {
      author: 'Adkins Stafford',
      text: 'Fugiat eu officia laboris non ipsum exercitation laboris consequat anim nulla qui culpa. Occaecat enim est consectetur ipsum fugiat adipisicing nostrud culpa Lorem exercitation ipsum nulla voluptate anim. Amet magna eiusmod duis elit excepteur aute.',
      date: '4/16/17'
    }
  ];

  constructor(
    private requestService: RequestService
  ) { }

  fetchComment(id: number|string) {
    return this.requestService.fetch('get', `/projects/${id}/comments`)
      .catch(() => []);
  }

  createComment(comment: CommentModel) {
    const data = Object.assign({
      comment: '',
      id: 0,
      projectId: 0
    }, comment);

    return this.requestService.fetch('post', '/comments', data);
  }

}
