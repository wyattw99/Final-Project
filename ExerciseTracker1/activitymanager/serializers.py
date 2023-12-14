# -*- coding: utf-8 -*-
"""
Created on Wed Dec 13 10:35:03 2023

@author: wwolf
"""

from rest_framework_json_api import serializers
from .models import User,Exercise

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = '__all__'

        
class ExerciseSerializer(serializers.ModelSerializer):
    class Meta:
        model = Exercise
        fields = '__all__'