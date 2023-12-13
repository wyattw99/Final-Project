# -*- coding: utf-8 -*-
"""
Created on Wed Dec 13 10:35:03 2023

@author: wwolf
"""

from rest_framework_json_api import serializers
from .models import User,Exercise

class UserSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = User
        feilds = 'first_name', 'last_name', 'user_birthday', 'user_height', 'user_weight'
        exclude = 'user_name', 'user_password'

        
class ExerciseSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Exercise
        fields = '__all__'