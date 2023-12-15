# -*- coding: utf-8 -*-
"""
Created on Wed Dec  6 16:44:38 2023

@author: wwolf
"""

from django.urls import path
from . import views

app_name='activitymanager'


urlpatterns = [
    #login page
    path("",views.login, name="login"),
    # home page
    path("<int:user_id>/home/", views.home, name="home"),
    #exercise details
    path("<int:user_id>/<int:exercise_id>/exercise/",views.web_exercise_detail,name="exercise_detail"),
    #user details
    path("<int:user_id>/user/",views.web_user_detail,name="user_detail"),
    #list of all users
    path("allusers/",views.web_all_users,name="all_users"),
    #create new user
    path("newuser/",views.web_new_user,name="new_user"),
    #create new exercise
    path("<int:user_id>/newexercise/",views.web_new_exercise,name="new_exercise"),
    #edit user
    path("<int:user_id>/edituser/",views.web_edit_user,name="edit_user"),
    #edit exercise
    path("<int:user_id>/<int:exercise_id>/editexercise/",views.web_edit_exercise,name="edit_exercise"),
    #all exercises by a user
    path("<int:user_id>/allexercises/",views.web_all_user_exercises, name="all_exercises"),
    #delete exercise
    path("<int:user_id>/<int:exercise_id>/deleteexercise/",views.web_delete_exercise, name = "delete_exercise"),
    #delete user
    path("<int:user_id>/deleteuser/",views.web_delete_user, name = "delete_user"),
    #api call for all users
    path("users/",views.api_user_list),
    #api call for a specific user
    path("users/<int:user_id>/", views.api_user_detail),
    #api call for all exercises
    path("exercises/",views.api_exercise_list),
    #api call for specific exercise
    path("exercises/<int:exercise_id>/",views.api_exercise_detail),
    
]
