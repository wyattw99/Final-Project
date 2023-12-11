from django.db import models


# Create your models here.

class User(models.Model):
    user_id = models.BigAutoField(primary_key=True)
    user_name = models.CharField(max_length=100)
    user_password = models.CharField(max_length=100)
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    user_birthday = models.DateField()
    user_height = models.IntegerField()
    user_weight = models.IntegerField()
    def __str__(self):
        return str(self.first_name + " " + self.last_name)
    
class Exercise(models.Model):
    exercise_id = models.BigAutoField(primary_key=True)
    exercise_name = models.CharField(max_length=255)
    exercise_type = models.CharField(max_length=50)
    exercise_distance = models.IntegerField()
    exercise_description = models.CharField(max_length=1000)
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    exercise_date = models.DateField
    exercise_time = models.IntegerField()
    def __str__(self):
        return self.exercise_name