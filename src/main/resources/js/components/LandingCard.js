import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
  rootdev: {
    minWidth: 275,
  },
  title: {
    fontSize: 14,
  },
  pos: {
    marginBottom: 12,
  },
});

export default function LandingCard() {
  const classes = useStyles();

  return (
    <Card className={classes.rootdev}>
      <CardContent>
        <Typography variant="h5" component="h2">
        Create customized countdowns for your special events.
        </Typography>
        <Typography variant="body2" component="p">
        Cherish your special events with a countdown.
        </Typography>
      </CardContent>
      <CardActions>
        <Button size="large">Get started</Button>
        <Button size="large">Learn more</Button>
      </CardActions>
    </Card>
  );
}