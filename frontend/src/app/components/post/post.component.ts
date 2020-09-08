import {Component, Input, OnInit} from '@angular/core';
import * as moment from 'moment';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  @Input() title;
  @Input() description;
  @Input() createdAt;


  constructor() { }

  ngOnInit(): void {
  }

  getFormat(date) {
    return moment(date).format('DD-MM-YYYY');
  }
}
