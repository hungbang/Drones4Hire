import { Injectable } from '@angular/core';
import {RequestService} from '../request.service/request.service';
import {CommentModel} from './comment.interface';
import {getFromObjectToObject} from "../../shared/common/common-methods";
import {AccountService} from "../account.service/account.service";

@Injectable()
export class CommentsService {
  constructor(
    private requestService: RequestService,
    private accountService: AccountService
  ) { }

  formatCommentToPreview(comments) {
    return comments.map((comment) => {
      let newComment: any = null;
      if (comment.account && comment.account.id) {
        newComment = getFromObjectToObject(comment, 'account:firstName', 'account:lastName', 'id', 'comment', 'createdAt', 'account:photoURL');
        newComment.accountId = comment.account.id;
        newComment.isPilot = this.accountService.isPilot(comment.account);
      } else {
        newComment = getFromObjectToObject(comment, 'comment', 'createdAt');
        newComment.firstName = 'Confidential';
        newComment.isPilot = false;
      }

      return newComment;
    });
  }

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
