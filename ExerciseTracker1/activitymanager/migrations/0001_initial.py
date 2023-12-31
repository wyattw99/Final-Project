# Generated by Django 4.2.7 on 2023-12-11 05:27

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = []

    operations = [
        migrations.CreateModel(
            name="User",
            fields=[
                ("user_id", models.BigAutoField(primary_key=True, serialize=False)),
                ("user_name", models.CharField(max_length=100)),
                ("user_password", models.CharField(max_length=100)),
                ("first_name", models.CharField(max_length=100)),
                ("last_name", models.CharField(max_length=100)),
                ("user_birthday", models.DateField()),
                ("user_height", models.IntegerField()),
                ("user_weight", models.IntegerField()),
            ],
        ),
        migrations.CreateModel(
            name="Exercise",
            fields=[
                ("exercise_id", models.BigAutoField(primary_key=True, serialize=False)),
                ("exercise_name", models.CharField(max_length=255)),
                ("exercise_type", models.CharField(max_length=50)),
                ("exercise_distance", models.IntegerField()),
                ("exercise_description", models.CharField(max_length=1000)),
                ("exercise_time", models.IntegerField()),
                (
                    "user",
                    models.ForeignKey(
                        on_delete=django.db.models.deletion.CASCADE,
                        to="activitymanager.user",
                    ),
                ),
            ],
        ),
    ]
