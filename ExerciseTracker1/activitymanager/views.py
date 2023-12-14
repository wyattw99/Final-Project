
# Create your views here.
from django.http import HttpResponse, JsonResponse
from .models import User, Exercise
from django.shortcuts import render,get_object_or_404,redirect
from .forms import EditUserForm, EditExerciseForm
from .serializers import UserSerializer, ExerciseSerializer
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser




def home(request):
    return HttpResponse("Activity Manager Home")
    '''will make homepage with links to different pages'''

def web_user_detail(request, user_id):
    user = get_object_or_404(User,user_id=user_id)
    return render(request, 'activitymanager/user_detail.html',{'user': user})

def web_all_users(request):
    user_list = User.objects.order_by("-first_name")
    output = "\n".join([user.__str__() for user in user_list])
    return HttpResponse(output);

def web_new_user(request):
    if request.method =='POST':
        form = EditUserForm(request.POST)
        if form.is_valid():
            newuser = form.save()
            userId = newuser.user_id
        return redirect('/activitymanager/{}/user/'.format(userId))
    
    else:
        form = EditUserForm()
    return render(request, 'activitymanager/create_user.html',{'form':form})

def web_edit_user(request,user_id):
    user = User.objects.get(user_id = user_id)
    if request.method =='POST':
        form = EditUserForm(request.POST, instance=user)
        if form.is_valid():
            form.save()
            return redirect('/activitymanager/{}/user/'.format(user_id))
    else:
        form = EditUserForm()
    return render(request,'activitymanager/edit_user.html',{'form':form})

def web_exercise_detail(request, exercise_id):
    exercise = get_object_or_404(Exercise,exercose_id=exercise_id)
    return render(request, 'activitymanager/exercise_detail.html',{'exercise': exercise})

def web_new_exercise(request):
    if request.method =='POST':
        form = EditExerciseForm(request.POST)
        if form.is_valid():
            newexercise = form.save()
            exerciseID = newexercise.exercise_id
        return redirect('/activitymanager/{}/exercise/'.format(exerciseID))
    
    else:
        form = EditExerciseForm()
    return render(request, 'activitymanager/create_exercise.html',{'form':form}) 

def web_edit_exercise(request,exercise_id):
    exercise = Exercise.objects.get(exercise_id = exercise_id)
    if request.method =='POST':
        form = EditExerciseForm(request.POST, instance=exercise)
        if form.is_valid():
            form.save()
            return redirect('/activitymanager/{}/exercise/'.format(exercise_id))
    else:
        form = EditExerciseForm()
    return render(request,'activitymanager/edit_exercise.html',{'form':form})

@csrf_exempt
def api_user_list(request):      
    if request.method =='GET':
        users = User.objects.all()
        serializer = UserSerializer(users, many=True)
        return JsonResponse(serializer.data, safe=False)
    elif request.method =='POST':
        data = JSONParser().parse(request)
        serializer = UserSerializer(data = data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status = 201)
        return JsonResponse(serializer.errors, status = 400)
    
@csrf_exempt
def api_user_detail(request, user_id):
    try:
        user = User.objects.get(user_id=user_id)
    except User.DoesNotExist:
        return HttpResponse(status=404)

    if request.method == 'GET':
        serializer = UserSerializer(user)
        return JsonResponse(serializer.data)
    
    elif request.method == 'PUT':
        data = JSONParser().parse(request)
        serializer = UserSerializer(user, data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data)
        return JsonResponse(serializer.errors, status=400)
    
    elif request.method == 'DELETE':
        user.delete()
        return HttpResponse(status=204)
        
    
@csrf_exempt    
def api_exercise_list(request):      
    if request.method =='GET':
        exercises = Exercise.objects.all()
        serializer = ExerciseSerializer(exercises, many=True)
        return JsonResponse(serializer.data, safe=False)
    elif request.method =='POST':
        data = JSONParser().parse(request)
        serializer = ExerciseSerializer(data = data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status = 201)
        return JsonResponse(serializer.errors, status = 400)
    
@csrf_exempt
def api_exercise_detail(request, exercise_id):
    try:
        exercise = Exercise.objects.get(exercise_id=exercise_id)
    except exercise.DoesNotExist:
        return HttpResponse(status=404)

    if request.method == 'GET':
        serializer = ExerciseSerializer(exercise)
        return JsonResponse(serializer.data)
    
    elif request.method == 'PUT':
        data = JSONParser().parse(request)
        serializer = ExerciseSerializer(exercise, data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data)
        return JsonResponse(serializer.errors, status=400)
    
    elif request.method == 'DELETE':
        exercise.delete()
        return HttpResponse(status=204)

    