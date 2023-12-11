# -*- coding: utf-8 -*-
"""
Created on Sun Dec 10 22:44:24 2023

@author: wwolf
"""

from django import forms
from .models import User, Exercise

class EditUserForm(forms.ModelForm):
    class Meta:
        model = User
        fields = ['user_name', 'first_name', 'last_name', 'user_birthday', 'user_height', 'user_weight']

class EditExerciseForm(forms.ModelForm):
    class Meta:
        model = Exercise
        fields = ['exercise_name', 'exercise_type', 'exercise_distance', 'exercise_description', 'user', 'exercise_date', 'exercise_time']
        
        