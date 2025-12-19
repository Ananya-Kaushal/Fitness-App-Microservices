import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router';
import { Card, CardContent, Grid, Typography } from '@mui/material';
import { getActivities } from '../services/api';

const ActivityList = () => {

  const [activities, setActivities] = useState([]);
  const navigate = useNavigate();

  const fetchActivities = async () => {
    try {
      const response = await getActivities();
      setActivities(response.data);
    } catch (error) {
      console.error(error);
    }
  }

  useEffect(() => {
    fetchActivities();
  }, []);

  return (
        <Grid container spacing={2}>
            {activities.map((activity) => (
                // FIX APPLIED HERE: Added key={activity.id}
                <Grid 
                    key={activity.id} // <--- UNIQUE KEY ADDED
                    item // Added 'item' prop to make it a child of a Grid container
                    xs={12} // Adjusted to be a grid item
                    sm={6} 
                    md={4}
                >
                    <Card 
                        sx={{cursor: 'pointer'}}
                        onClick={() => navigate( `/activities/${activity.id}`)}
                    >
                        <CardContent>
                            <Typography variant='h6'>{activity.type}</Typography>
                            <Typography>Duration: {activity.duration}</Typography>
                            <Typography>Calories: {activity.caloriesBurned}</Typography>
                        </CardContent>
                    </Card>
                </Grid>
            ))}
        </Grid>
    )
}

export default ActivityList