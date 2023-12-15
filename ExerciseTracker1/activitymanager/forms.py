# -*- coding: utf-8 -*-
"""
Created on Sun Dec 10 16:07:44 2023

@author: wwolf
"""


from django import forms
from .models import User, Exercise

class EditUserForm(forms.ModelForm):
    class Meta:
        model = User
        fields = ['user_name', 'first_name', 'last_name', 'user_birthday', 'user_height', 'user_weight']

class NewUserForm(forms.ModelForm):
    class Meta:
        model = User
        fields = ['user_name', 'user_password', 'first_name', 'last_name', 'user_birthday', 'user_height', 'user_weight']

class EditExerciseForm(forms.ModelForm):
    class Meta:
        model = Exercise
        fields = ['exercise_name', 'exercise_type', 'exercise_distance', 'exercise_description', 'user', 'exercise_date', 'exercise_time', 'exercise_intensity']
        
        
class LoginForm(forms.ModelForm):
    class Meta:
        model = User
        fields = ['user_name', 'user_password']
        