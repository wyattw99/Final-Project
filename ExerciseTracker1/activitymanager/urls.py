# -*- coding: utf-8 -*-
"""
Created on Wed Dec  6 16:44:38 2023

@author: wwolf
"""

from django.urls import path

from . import views

app_name='activitymanager'

urlpatterns = [
    path("", views.home, name="home"),
    path("<int:exercise_id>/exercise/",views.exercise_detail,name="exercise_detail"),
    path("<int:user_id>/user/",views.user_detail,name="user_detail"),
    path("allusers/",views.all_users,name="all_users"),
    path("newuser/",views.new_user,name="new_user"),
    path("<int:user_id>/edituser/",views.edit_user,name="edit_user"),
]