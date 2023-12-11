# -*- coding: utf-8 -*-
"""
Created on Wed Dec  6 16:44:38 2023

@author: wwolf
"""

from django.urls import path

from . import views

#app_name='activitymanager'

urlpatterns = [
    path("", views.home, name="home"),
]
