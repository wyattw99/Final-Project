
# Create your views here.
from django.http import HttpResponse, JsonResponse
from .models import User, Exercise
from django.shortcuts import render,get_object_or_404,redirect
from .forms import EditUserForm, EditExerciseForm
from .serializers import UserSerializer, ExerciseSerializer
from rest_framework import viewsets




def home(request):
    return HttpResponse("Activity Manager Home")
    '''will make homepage with links to different pages'''

def user_detail(request, user_id):
    user = get_object_or_404(User,user_id=user_id)
    return render(request, 'activitymanager/user_detail.html',{'user': user})

def all_users(request):
    user_list = User.objects.order_by("-first_name")
    output = "\n".join([user.__str__() for user in user_list])
    return HttpResponse(output);

def new_user(request):
    if request.method =='POST':
        form = EditUserForm(request.POST)
        if form.is_valid():
            newuser = form.save()
            userId = newuser.user_id
        return redirect('/activitymanager/{}/user/'.format(userId))
    
    else:
        form = EditUserForm()
    return render(request, 'activitymanager/create_user.html',{'form':form})

def edit_user(request,user_id):
    user = User.objects.get(user_id = user_id)
    if request.method =='POST':
        form = EditUserForm(request.POST, instance=user)
        if form.is_valid():
            form.save()
            return redirect('/activitymanager/{}/user/'.format(user_id))
    else:
        form = EditUserForm()
    return render(request,'activitymanager/edit_user.html',{'form':form})

def exercise_detail(request, exercise_id):
    exercise = get_object_or_404(Exercise,exercose_id=exercise_id)
    return render(request, 'activitymanager/exercise_detail.html',{'exercise': exercise})

def new_exercise(request):
    if request.method =='POST':
        form = EditExerciseForm(request.POST)
        if form.is_valid():
            newexercise = form.save()
            exerciseID = newexercise.exercise_id
        return redirect('/activitymanager/{}/exercise/'.format(exerciseID))
    
    else:
        form = EditExerciseForm()
    return render(request, 'activitymanager/create_exercise.html',{'form':form}) 

def edit_exercise(request,exercise_id):
    exercise = Exercise.objects.get(exercise_id = exercise_id)
    if request.method =='POST':
        form = EditExerciseForm(request.POST, instance=exercise)
        if form.is_valid():
            form.save()
            return redirect('/activitymanager/{}/exercise/'.format(exercise_id))
    else:
        form = EditExerciseForm()
    return render(request,'activitymanager/edit_exercise.html',{'form':form})

class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer
    
class ExerciseViewSet(viewsets.ModelViewSet):
    queryset = Exercise.objects.all()
    serializer_class = ExerciseSerializer

    