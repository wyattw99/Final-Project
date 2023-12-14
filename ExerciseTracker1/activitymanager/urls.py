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
    path("<int:exercise_id>/exercise/",views.web_exercise_detail,name="exercise_detail"),
    path("<int:user_id>/user/",views.web_user_detail,name="user_detail"),
    path("allusers/",views.web_all_users,name="all_users"),
    path("newuser/",views.web_new_user,name="new_user"),
    path("<int:user_id>/edituser/",views.web_edit_user,name="edit_user"),
    path("users/",views.api_user_list),
    path("users/<int:user_id>/", views.api_user_detail),
    path("users/",views.api_user_list),
    path("users/<int:user_id>/", views.api_user_detail),
    path("exercises/",views.api_exercise_list),
    path("exercises/<int:exercise_id>/",views.api_exercise_detail),
    
]
