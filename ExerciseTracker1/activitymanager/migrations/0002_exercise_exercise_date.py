# Generated by Django 4.2.7 on 2023-12-11 05:38

from django.db import migrations, models
import django.utils.timezone


class Migration(migrations.Migration):

    dependencies = [
        ("activitymanager", "0001_initial"),
    ]

    operations = [
        migrations.AddField(
            model_name="exercise",
            name="exercise_date",
            field=models.DateField(default=django.utils.timezone.now),
            preserve_default=False,
        ),
    ]